import { Cookies } from 'react-cookie';

interface ICookieOption {
	path?: string;
	maxAge?: number;
}

const cookies = new Cookies();

export const cookie = {
	getItem: (key: string) => cookies.get(key),
	setItem: (key: string, value: string, option?: ICookieOption) =>
		cookies.set(key, value, { ...option }),
	removeItem: (key: string) => cookies.remove(key),
};

export const changeToken = (accessToken: string, refreshToken: string) => {
	cookie.removeItem('accessToken');
	cookie.removeItem('refreshToken');
	cookie.setItem('accessToken', accessToken, { maxAge: 3600 });
	cookie.setItem('refreshToken', refreshToken, { maxAge: 3600 });
};
