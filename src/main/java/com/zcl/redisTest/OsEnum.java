package com.zcl.redisTest;

import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import lombok.Getter;

public enum OsEnum {
    IOS(0, "IOS"),
    ANDROID(1, "ANDROID");
	@Getter
    private int code;
	@Getter
    private String name;

    OsEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final ImmutableMap<Integer, OsEnum> codeMap;

    static {
        ImmutableMap.Builder<Integer, OsEnum> builder = ImmutableMap.builder();
        for (OsEnum each : OsEnum.values()) {
            builder.put(each.getCode(), each);
        }
        codeMap = builder.build();
    }

    /**
     * 通过code获取对应的OsEnum
     *
     * @return
     */
    public static OsEnum codeOf(Integer code) {
        if (code == null) {
            return null;
        }
        return Optional.of(codeMap.get(code)).get();
    }
    public static void main(String[] args) {
    	System.out.println(Optional.ofNullable(codeMap.get(4)).isPresent());
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static ImmutableMap<Integer, OsEnum> getCodemap() {
		return codeMap;
	}
    
}
