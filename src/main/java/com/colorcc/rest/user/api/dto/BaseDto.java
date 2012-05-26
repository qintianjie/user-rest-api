package com.colorcc.rest.user.api.dto;

public interface BaseDto<T, V> {

	public V transferTypetoBean(T t, Object ... objects);

	public T transferBeanToType(V v, Object ...objects );

}
