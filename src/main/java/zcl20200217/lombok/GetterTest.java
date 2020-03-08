package zcl20200217.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author zhoucl
 * 2020/3/8 下午8:59
 */
public class GetterTest {

    @Getter(lazy = true)
    private  final String field1 = "clzhou01";

    @Getter(
            value = AccessLevel.PRIVATE,
            onMethod_={@NonNull}
    )
    private String field2;
}
