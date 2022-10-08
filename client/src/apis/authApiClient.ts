import axios, { AxiosRequestHeaders } from 'axios';
import apiClient from './apiClient';
import { cookie } from 'utils/cookie';
import { NewQuestionSubmitData } from 'pages/Interveiw/Question/QuestionWrite';
import {
	refreshDeleteApi,
	refreshPostApi,
	refreshPutApi,
} from 'utils/apiUtilFunctions';
import { EditedQuestionSubmitData } from 'pages/Interveiw/Question/QuestionEdit';

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

// postQuestion
export const postQuestionApi = async (data: NewQuestionSubmitData) => {
	try {
		const response = await authApiClient.post('/api/v1/questions', data);

		if (response.status === 200) {
			refreshPostApi('/api/v1/questions', data);
		}

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('질문 작성 실패');
	}
};

// putQuestion
export const putQuestionApi = async (data: EditedQuestionSubmitData) => {
	try {
		const response = await authApiClient.put('/api/v1/questions', data);

		if (response.status === 200) {
			refreshPutApi('/api/v1/questions', data);
		}

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
		if (response.status === 200) {
			refreshDeleteApi(`/api/v1/questions/${id}`);
		}

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('글 삭제 실패');
	}
};
