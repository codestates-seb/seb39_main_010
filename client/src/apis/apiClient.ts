import axios from 'axios';
import { LoginSubmitForm, SignupSubmitForm } from 'types';

const apiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
});

apiClient.interceptors.response.use(
	(res) => res,
	async (err) => {
		const {
			response: { status },
		} = err;

		if (status === 404) {
			window.location.href = '/404';
		}

		return Promise.reject(err);
	}
);

// signup
export const signupApi = async (formData: SignupSubmitForm) => {
	try {
		const response = await apiClient.post('/api/v1/users/signup', formData);
		return response.status;
	} catch (error) {
		if (axios.isAxiosError(error)) {
			const status = error.response?.status;
			if (status === 409) {
				throw window.alert(
					'이미 존재하는 아이디 또는 닉네임입니다. 다른 아이디 또는 닉네임을 사용해주세요.'
				);
			}
		} else {
			throw new Error('회원가입 에러');
		}
	}
};

export const emailAuthenticationApi = async (email: string) => {
	try {
		const response = await apiClient.post('/api/v1/users/exists-email', {
			email,
		});

		if (response.status === 200)
			return window.alert('이메일 인증에 성공했습니다.');
	} catch (error) {
		if (axios.isAxiosError(error)) {
			const status = error.response?.status;
			if (status === 409) {
				throw window.alert(
					'이미 가입된 이메일입니다. 다른 이메일로 가입해주세요.'
				);
			}
		} else {
			throw new Error('이메일 인증 에러');
		}
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
export const getQuestionListApi = async (page: number) => {
	try {
		const response = await apiClient.get(`/api/v1/questions?page=${page}`);

		return response.data;
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

// getUserProfile
export const getUserProfile = async (id?: number) => {
	try {
		const response = await apiClient.get(`/api/v1/users/${id}`);

		return response.data.data;
	} catch (error) {
		throw new Error('유저 정보 조회에 실패했습니다.');
	}
};

export default apiClient;
