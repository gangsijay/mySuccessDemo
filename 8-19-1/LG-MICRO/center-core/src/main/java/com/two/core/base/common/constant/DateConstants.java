package com.two.core.base.common.constant;

import java.text.SimpleDateFormat;

/**功能描述:时间格式常量类
 * @Auther: Tjee
 * @Date: 2018/4/12 22:20
 * @Description:
 */
public class DateConstants {

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_NUM  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_NUM() {
        return (SimpleDateFormat) DATE_FORMAT_NUM.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_NUM_SHORT  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_NUM_SHORT() {
        return (SimpleDateFormat) DATE_FORMAT_NUM_SHORT.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_NUM_YM  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyyMM");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_NUM_YM() {
        return (SimpleDateFormat) DATE_FORMAT_NUM_YM.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_NUM_HMS  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("HHmmss");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_NUM_HMS() {
        return (SimpleDateFormat) DATE_FORMAT_NUM_HMS.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static SimpleDateFormat DATE_FORMAT() {
        return (SimpleDateFormat) DATE_FORMAT.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_SHORT  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_SHORT() {
        return (SimpleDateFormat) DATE_FORMAT_SHORT.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_CH_SHORT  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("MM月dd日");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_CH_SHORT() {
        return (SimpleDateFormat) DATE_FORMAT_CH_SHORT.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_MD  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("MM-dd");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_MD() {
        return (SimpleDateFormat) DATE_FORMAT_MD.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_HM  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_HM() {
        return (SimpleDateFormat) DATE_FORMAT_HM.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_HMS  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_HMS() {
        return (SimpleDateFormat) DATE_FORMAT_HMS.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_YEAR  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyy");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_YEAR() {
        return (SimpleDateFormat) DATE_FORMAT_YEAR.get();
    }

    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_MONTH  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("MM");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_MONTH() {
        return (SimpleDateFormat) DATE_FORMAT_MONTH.get();
    }
    @SuppressWarnings("rawtypes")
    private static ThreadLocal DATE_FORMAT_YMDHM  = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };
    public static SimpleDateFormat DATE_FORMAT_YMDHM() {
        return (SimpleDateFormat) DATE_FORMAT_YMDHM.get();
    }
}
