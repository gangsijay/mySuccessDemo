package com.zhm.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

/**
 * Created by haiming.zhuang on 2016/7/13.
 */
public class EncryptUtils {

    /**
     * 用MD5算法进行加密
     * @param str 需要加密的字符串
     * @return MD5加密后的结果
     */
    public static String encodeMD5String(String str) {
        return encode(str, "MD5");
    }

    /**
     * 用SHA算法进行加密
     * @param str 需要加密的字符串
     * @return SHA加密后的结果
     */
    public static String encodeSHAString(String str) {
        return encode(str, "SHA");
    }

    /**
     * 用base64算法进行加密
     * @param str 需要加密的字符串
     * @return base64加密后的结果
     */
    public static String encodeBase64String(String str) {
        BASE64Encoder encoder =  new BASE64Encoder();
        return encoder.encode(str.getBytes());
    }

    /**
     * 用base64算法进行解密
     * @param str 需要解密的字符串
     * @return base64解密后的结果
     * @throws IOException
     */
    public static String decodeBase64String(String str) throws IOException {
        BASE64Decoder encoder =  new BASE64Decoder();
        return new String(encoder.decodeBuffer(str));
    }

    private static String encode(String str, String method) {
        MessageDigest md = null;
        String dstr = null;
        try {
            md = MessageDigest.getInstance(method);
            md.update(str.getBytes());
            dstr = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return dstr;
    }
//
//    public static void main(String[] args) throws IOException {
//        String user = "123456";
//        System.out.println("原始字符串 " + user);
//        System.out.println("MD5加密 " + encodeMD5String(user));
//        System.out.println("SHA加密 " + encodeSHAString(user));
//        String base64Str = encodeBase64String(user);
//        System.out.println("Base64加密 " + base64Str);
//        System.out.println("Base64解密 " + decodeBase64String(base64Str));
//    }
        public static void main(String[] args) {
            Hashtable<String, String> HashEnv = new Hashtable<String, String>();
            String LDAP_URL = "ldap://192.168.1.171"; //LDAP访问地址
            String adminName = "haiming.zhuang@trinasolar.com"; //注意用户名的写法
            String adminPassword = "zhmlvft.1413"; //密码

            HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); //LDAP访问安全级别
            HashEnv.put(Context.SECURITY_PRINCIPAL, adminName); //AD User
            HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); //AD Password
            HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); //LDAP工厂类
            HashEnv.put(Context.PROVIDER_URL, LDAP_URL);

            try {
                LdapContext ctx = new InitialLdapContext(HashEnv, null);
                SearchControls searchCtls = new SearchControls(); //Create the search controls
                searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); //Specify the search scope

                String searchFilter = "(&(objectClass=User)(userPrincipalName="+adminName+"))"; //specify the LDAP search filter

                String searchBase = "dc=trinasolar,dc=com"; //Specify the Base for the search//搜索域节点
                int totalResults = 0;

                String returnedAtts[] = {
                        "cn", "displayName", "distinguishedName","name", "primaryGroupID",
                        "pwdLastSet", "sAMAccountName", "sAMAccountType","sn","givenName",
                        "userAccountControl", "userPrincipalName", "whenChanged", "whenCreated"}; //定制返回属性

                searchCtls.setReturningAttributes(returnedAtts); //设置返回属性集

                //Search for objects using the filter
                NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter,searchCtls);

                if(answer==null||answer.equals(null)){
                    System.out.println("answer is null");
                }else{
                    System.out.println("answer not null");
                }

                System.out.println(answer.hasMoreElements());

                while (answer.hasMoreElements()) {
                    SearchResult sr = (SearchResult) answer.next();
                    System.out.println("************************************************");
                    System.out.println(sr.getName());

                    Attributes Attrs = sr.getAttributes();
                    if (Attrs != null) {
                        try {
                            for (NamingEnumeration<? extends Attribute> ne = Attrs.getAll(); ne.hasMore(); ) {
                                Attribute Attr = (Attribute) ne.next();

                                System.out.println(" AttributeID=" + Attr.getID().toString());

                                //读取属性值
                                for (NamingEnumeration<?> e = Attr.getAll(); e.hasMore();totalResults++) {
                                    System.out.println("    AttributeValues=" + e.next().toString());
                                }
                                System.out.println("    ---------------");

                                //读取属性值
                                NamingEnumeration<?> values = Attr.getAll();
                                if (values != null) { // 迭代
                                    while (values.hasMoreElements()) {
                                        System.out.println("    AttributeValues=" + values.nextElement());
                                    }
                                }
                                System.out.println("    ---------------");
                            }
                        }
                        catch (NamingException e) {
                            System.err.println("Throw Exception : " + e);
                        }
                    }
                }
                System.out.println("Number: " + totalResults);
                ctx.close();
            }

            catch (NamingException e) {
                e.printStackTrace();
                System.err.println("Throw Exception : " + e);
            }
    }
}
