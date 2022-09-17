import axios from 'axios';

// header 추가 필요
const authApiClient = axios.create({
	baseURL: `${process.env.REACT_APP_BASE_URL}`,
});
