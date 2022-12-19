import axios, { AxiosRequestHeaders } from 'axios';
import { cookie } from 'utils/cookie';
import { NewQuestionSubmitData } from 'pages/Interveiw/Question/QuestionWrite';
import { EditedQuestionSubmitData } from 'pages/Interveiw/Question/QuestionEdit';
import apiClient from './apiClient';

export const refreshHeaders: AxiosRequestHeaders = {
	Authorization: `Bearer ${cookie.getItem('accessToken')}`,
	Refresh: `${cookie.getItem('refreshToken')}`,
};

export const headers: AxiosRequestHeaders = {
	Authorization: `Bearer ${cookie.getItem('accessToken')}`,
};

// header 추가 필요
export const authApiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
	headers: headers,
});

const getRefreshToken = async (): Promise<string | void> => {
	try {
		const response = await apiClient.post(
			'/api/v1/token/refresh',
			{},
			{
				headers: { Refresh: `${cookie.getItem('refreshToken')}` },
			}
		);

		const accessToken = response.headers.authorization.split(' ')[1];
		const refreshToken = response.headers.refresh;

		cookie.removeItem('accessToken');
		cookie.removeItem('refreshToken');

		cookie.setItem('accessToken', accessToken);
		cookie.setItem('refreshToken', refreshToken);

		return accessToken;
	} catch (e) {
		cookie.removeItem('accessToken');
		cookie.removeItem('refreshToken');
	}
};

authApiClient.interceptors.response.use(
	(res) => res,
	async (err) => {
		const {
			config,
			response: { status, data },
		} = err;

		const accessToken = await getRefreshToken();

		if (!config.sent && status === 401 && data.includes('Expired JWT')) {
			config.sent = true;
			config.headers.Authorization = `Bearer ${accessToken}`;
		}

		return authApiClient(config);
	}
);

// postQuestion
export const postQuestionApi = async (data: NewQuestionSubmitData) => {
	try {
		const response = await authApiClient.post('/api/v1/questions', data);

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('질문 작성 실패');
	}
};

// putQuestion
export const putQuestionApi = async (
	data: EditedQuestionSubmitData,
	id?: string
) => {
	try {
		const response = await authApiClient.put(`/api/v1/questions/${id}`, data);

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('질문 작성 실패');
	}
};

// deleteQuestion
export const deleteQuestionApi = async (id?: string) => {
	try {
		const response = await authApiClient.delete(`/api/v1/questions/${id}`);

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('글 삭제 실패');
	}
};
