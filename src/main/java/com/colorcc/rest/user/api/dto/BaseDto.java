package com.colorcc.rest.user.api.dto;


public interface BaseDto<T, V extends View> {

	public V transferTypetoBean(T t, Object ... objects);

	public T transferBeanToType(V v, Object ...objects );

}
