import axios from 'axios';
import { LoginSubmitForm, SignupSubmitForm } from 'types';

const apiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
});

// signup
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

// login
export const loginApi = async (formData: LoginSubmitForm) => {
	try {
		return await apiClient.post('/api/v1/users/login', formData);
	} catch (error) {
		console.log(error);
		throw new Error('로그인 에러');
	}
};

// getQuestionList
export const getQuestionListApi = async () => {
	try {
		const response = await apiClient.get('/api/v1/questions');

		return response.data.content;
	} catch (error) {
		throw new Error('질문 목록 조회 실패');
	}
};

// getFilteredQuestionList
export const getFilteredQuestionList = async (
	keyword: string,
	orderby?: string,
	category?: string
) => {
	try {
		if (!orderby && category) {
			const response = await apiClient.get(
				`/api/v1/questions/search?searchType=title&keyword=${keyword}&category=${category}`
			);
			return response.data.content;
		} else if (!category && orderby) {
			const response = await apiClient.get(
				`/api/v1/questions/search?searchType=title&keyword=${keyword}&orderby=${orderby}`
			);
			return response.data.content;
		} else if (orderby && category) {
			const response = await apiClient.get(
				`/api/v1/questions/search?searchType=title&keyword=${keyword}&orderby=${orderby}&category=${category}`
			);
			return response.data.content;
		} else {
			const response = await apiClient.get(
				`/api/v1/questions/search?searchType=title&keyword=${keyword}`
			);
			return response.data.content;
		}
	} catch (error) {
		throw new Error('질문 정렬 실패');
	}
};

// getQuestion
export const getQuestionApi = async (id?: string) => {
	try {
		return await apiClient.get(`/api/v1/questions/${id}`);
	} catch (error) {
		console.log(error);
		throw new Error('글 조회 실패');
	}
};

export default apiClient;
