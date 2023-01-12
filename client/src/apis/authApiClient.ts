import axios from 'axios';
import { cookie } from 'utils/cookie';
import { NewQuestionSubmitData } from 'pages/Interveiw/Question/QuestionWrite';
import { EditedQuestionSubmitData } from 'pages/Interveiw/Question/QuestionEdit';

export const authApiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
});

const getNewToken = async (): Promise<string | void> => {
	try {
		const response = await authApiClient.post('/api/v1/token/refresh', {});

		const accessToken = response.headers.authorization.split(' ')[1];
		const refreshToken = response.headers.refresh;

		localStorage.removeItem('accessToken');
		localStorage.setItem('accessToken', accessToken);

		cookie.removeItem('refreshToken');
		cookie.setItem('refreshToken', refreshToken, { maxAge: 3600 });

		return accessToken;
	} catch (e) {
		localStorage.removeItem('persistUserAtom');
		localStorage.removeItem('accessToken');
		cookie.removeItem('refreshToken');
	}
};

authApiClient.interceptors.request.use(
	(config) => {
		const accessToken = localStorage.getItem('accessToken');
		const refreshToken = cookie.getItem('refreshToken');
		const { url } = config;

		if (url === '/api/v1/token/refresh' && refreshToken) {
			config.headers = { Refresh: `${refreshToken}` };
		} else if (accessToken && refreshToken) {
			config.headers = {
				Authorization: `Bearer ${accessToken}`,
				'Content-Type': 'application/json',
			};
		}

		return config;
	},
	(err) => {
		return Promise.reject(err);
	}
);

authApiClient.interceptors.response.use(
	(res) => res,
	async (err) => {
		const {
			config,
			response: { status, data },
		} = err;

		if (
			config.url === '/api/v1/token/refresh' ||
			status !== 401 ||
			config.sent
		) {
			return Promise.reject(err);
		}

		config.sent = true;
		const accessToken = await getNewToken();

		if (accessToken && status === 401 && data.includes('Expired JWT')) {
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

// postQuestionLike
export const postQuestionLikeApi = async (id?: string) => {
	try {
		const response = await authApiClient.post(
			`/api/v1/questions/${id}/sympathy`,
			{}
		);

		console.log(response);
	} catch (error) {
		throw new Error('좋아요 실패');
	}
};

// postComment
export const postCommentApi = async (
	type: string,
	comment: string,
	id?: string
) => {
	try {
		const response = await authApiClient.post(`/api/v1/${type}/${id}/answers`, {
			parentId: null,
			comment,
		});

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('댓글 작성 실패');
	}
};

// putComment
export const putCommentApi = async (
	type: string,
	comment?: string,
	id?: string,
	answerId?: number
) => {
	try {
		const response = await authApiClient.put(
			`/api/v1/${type}/${id}/answers/${answerId}`,
			{
				parentId: null,
				comment,
			}
		);

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('댓글 작성 실패');
	}
};

// deleteComment
export const deleteCommentApi = async (
	type: string,
	id?: string,
	answerId?: number
) => {
	try {
		const response = await authApiClient.delete(
			`/api/v1/${type}/${id}/answers/${answerId}`
		);

		return response;
	} catch (error) {
		console.log(error);
		throw new Error('댓글 작성 실패');
	}
};

export const postQuestionCommentLikeApi = async (
	questionId?: string,
	answerId?: string
) => {
	try {
		const response = await authApiClient.post(
			`/api/v1/questions/${questionId}/sympathy/${answerId}`,
			{}
		);

		console.log(response);
	} catch (error) {
		throw new Error('댓글 좋아요 실패');
	}
};

// patchProfileInfo
export const patchCompanyInfo = async (
	favoriteCompany: string | null,
	id?: number
) => {
	try {
		const response = await authApiClient.patch(`/api/v1/users/${id}`, {
			favoriteCompany,
		});

		return response.data.data;
	} catch (error) {
		throw new Error('유저 정보 수정을 실패했습니다.');
	}
};

export const patchIntroductionInfo = async (
	selfIntroductions: string | null,
	id?: number
) => {
	try {
		const response = await authApiClient.patch(`/api/v1/users/${id}`, {
			selfIntroductions,
		});

		return response.data.data;
	} catch (error) {
		throw new Error('유저 정보 수정을 실패했습니다.');
	}
};

export const patchUserInfo = async (
	favoriteCompany: string | null,
	selfIntroductions: string | null,
	id?: number
) => {
	try {
		const response = await authApiClient.patch(`/api/v1/users/${id}`, {
			favoriteCompany,
			selfIntroductions,
		});

		return response.data.data;
	} catch (error) {
		throw new Error('유저 정보 수정을 실패했습니다.');
	}
};
