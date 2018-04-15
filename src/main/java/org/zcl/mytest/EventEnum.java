package org.zcl.mytest;

import java.util.Objects;

import lombok.Getter;

/**
 * 
 * @author Administrator
 *
 */
public enum EventEnum {
    READ_NEWS("新闻资讯阅读", 1),
    SHARE("分享资讯给好友", 1),
    COMMENT("新闻资讯评论", 1),
    LIKE("新闻资讯点赞", 1),
    SERVICE("使用本地服务", 1),
    INVITED("邀请手机注册用户", 1),
    REGISTER("注册账号", 2),
    COLLECT("首次收藏新闻资讯", 2),
    FOCUS_ON("首次关注活动", 2),
    READ_MESSAGE("首次查看消息", 2),
    CHANGE_ICON("首次修改头像", 2),
    CHECK_IN("签到", 3),
    SHOPPING("积分商城兑换", 0),
    SHOPPING_FAIL("积分兑换失败", 0),
    ADD_INT("积分增加", 0),
    SUB_INT("积分减少", 0),
    ADD_EXP("经验增加", 0),
    SUB_EXP("经验减少", 0);


    @Getter
    private String event;

    @Getter
    private Integer eventType;

    EventEnum(String event, Integer eventType) {
        this.event = event;
        this.eventType = eventType;
    }

    /**
     * 通过code获取对应的EventEnum
     *
     * @return
     */
    public static EventEnum getByEvent(String event) {
        for (EventEnum eventEnum : EventEnum.values()) {
            if (Objects.equals(eventEnum.getEvent(), event)) {
                return eventEnum;
            }
        }
        return null;
    }

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
    
}
