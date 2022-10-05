import axios from 'axios';
import { LoginSubmitForm, SignupSubmitForm } from 'types';

const apiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
});

export const signupApi = async (formData: SignupSubmitForm) => {
	try {
		return await apiClient.post('/api/v1/users/signup', formData);
	} catch (error) {
		console.log(error);
		throw new Error('회원가입 에러');
	}
};

export const emailAuthenticationApi = async (email: string) => {
	try {
		return await apiClient.post('/api/v1/users/exists-email', {
			email,
		});
	} catch (error) {
		console.log(error);
		throw new Error('이메일 인증 에러');
	}
};

export const loginApi = async (formData: LoginSubmitForm) => {
	try {
		return await apiClient.post('/api/v1/users/login', formData);
	} catch (error) {
		console.log(error);
		throw new Error('로그인 에러');
	}
};

export default apiClient;
