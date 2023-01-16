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
