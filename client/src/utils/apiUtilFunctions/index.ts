import apiClient from 'apis/apiClient';
import { refreshHeaders } from 'apis/authApiClient';
import { changeToken } from 'utils/cookie';

export const refreshPostApi = async (url: string, data: any) => {
	const refreshResponse = await apiClient.post(url, data, {
		headers: refreshHeaders,
	});
	const accessToken = refreshResponse.headers.authorization.split(' ')[1];
	const refreshToken = refreshResponse.headers.refresh;

	const newResponse = await apiClient.post(url, data, {
		headers: { Authorization: `Bearer ${accessToken}` },
	});
	changeToken(accessToken, refreshToken);

	return newResponse;
};

export const refreshPutApi = async (url: string, data: any) => {
	const refreshResponse = await apiClient.put(url, data, {
		headers: refreshHeaders,
	});
	const accessToken = refreshResponse.headers.authorization.split(' ')[1];
	const refreshToken = refreshResponse.headers.refresh;

	const newResponse = await apiClient.put(url, data, {
		headers: { Authorization: `Bearer ${accessToken}` },
	});
	changeToken(accessToken, refreshToken);

	return newResponse;
};

export const refreshPatchApi = async (url: string, data: any) => {
	const refreshResponse = await apiClient.patch(url, data, {
		headers: refreshHeaders,
	});
	const accessToken = refreshResponse.headers.authorization.split(' ')[1];
	const refreshToken = refreshResponse.headers.refresh;

	const newResponse = await apiClient.patch(url, data, {
		headers: { Authorization: `Bearer ${accessToken}` },
	});
	changeToken(accessToken, refreshToken);

	return newResponse;
};

export const refreshDeleteApi = async (url: string) => {
	const refreshResponse = await apiClient.delete(url, {
		headers: refreshHeaders,
	});
	const accessToken = refreshResponse.headers.authorization.split(' ')[1];
	const refreshToken = refreshResponse.headers.refresh;

	const newResponse = await apiClient.delete(url, {
		headers: { Authorization: `Bearer ${accessToken}` },
	});
	changeToken(accessToken, refreshToken);

	return newResponse;
};
