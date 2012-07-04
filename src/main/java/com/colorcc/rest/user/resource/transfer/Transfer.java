package com.colorcc.rest.user.resource.transfer;

import com.colorcc.rest.user.resource.view.View;


public interface Transfer<T, V extends View> {

	public V transferTypetoBean(T t, Object ... objects);

	public T transferBeanToType(V v, Object ...objects );

}
