import { cookie } from 'utils/cookie';

export const getUser = () => {
	const userInfo = localStorage.getItem('persistUserAtom');
	const refreshToken = cookie.getItem('refreshToken');

	if (userInfo && refreshToken) {
		return JSON.parse(userInfo);
	} else {
		localStorage.removeItem('accessToken');
		localStorage.removeItem('persistUserAtom');
		return null;
	}
};
