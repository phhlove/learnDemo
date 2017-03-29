package com.phh.learnDemo.java;

import java.util.concurrent.atomic.AtomicLong;

public class UniqRequestIdGen {

    private static AtomicLong   lastId         = new AtomicLong();                                         // 自增id，用于requestId的生成过程
    private static final long   startTimeStamp = System.currentTimeMillis();                               // 启动加载时的时间戳，用于requestId的生成过程
//    private static final String ip             = LocalIpAddressUtil.resolveLocalAddress().getHostAddress(); // 本机ip地址，用于requestId的生成过程
    private static final String ip             = "";

    public static void main(String[] args) {
        System.out.println(resolveReqId());
    }

    private static String resolveReqId() {
        // 规则： hexIp(ip)base36(timestamp)-seq
        return hexIp(ip) + Long.toString(startTimeStamp, Character.MAX_RADIX) + "-" + lastId.incrementAndGet();
    }

    // 将ip转换为定长8个字符的16进制表示形式：255.255.255.255 -> FFFFFFFF
    private static String hexIp(String ip) {
        StringBuilder sb = new StringBuilder();
        for (String seg : ip.split("\\.")) {
            String h = Integer.toHexString(Integer.parseInt(seg));
            if (h.length() == 1) sb.append("0");
            sb.append(h);
        }
        return sb.toString();
    }

}
