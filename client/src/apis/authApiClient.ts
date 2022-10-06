import axios from 'axios';
import { cookie } from 'utils/cookie';

// header 추가 필요
const authApiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
	headers: {
		Authorization: `Bearer ${cookie.getItem('accessToken')}}`,
		Refresh: `${cookie.getItem('refreshToken')}`,
	},
});
