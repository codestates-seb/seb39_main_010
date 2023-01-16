import React, { useState } from 'react';
import styled from 'styled-components';
import { useSetRecoilState } from 'recoil';
import { loginModalAtom, userAtom } from 'recoil/atom';
import AuthInput from 'components/AuthInput/AuthInput';
import BasicButton from '../BasicButton/BasicButton';
import SocialButton from '../SocialButtons/SocialButtons';
import { useNavigate } from 'react-router-dom';
import { SubmitHandler, useForm } from 'react-hook-form';
import { LoginSubmitForm } from 'types';
import { loginApi } from 'apis/apiClient';
import { cookie } from 'utils/cookie';
import LoginModalFrame from './LoginModalFrame';

const LoginModal = () => {
	const { register, handleSubmit } = useForm<LoginSubmitForm>();
	const [loginError, setLoginError] = useState(false);
	const setIsLoginMoal = useSetRecoilState(loginModalAtom);
	const navigate = useNavigate();
	const setUserInfo = useSetRecoilState(userAtom);

	const onSubmit: SubmitHandler<LoginSubmitForm> = async (data) => {
		const response = await loginApi(data);

		if (!response.headers.authorization) {
			setLoginError(true);
			return;
		}
		const accessToken = response.headers.authorization.split(' ')[1];
		const refreshToken = response.headers.refresh;

		localStorage.setItem('accessToken', accessToken);
		cookie.setItem('refreshToken', refreshToken, { maxAge: 3600 });
		setUserInfo(response.data);

		setIsLoginMoal(false);
		setLoginError(false);
	};

	return (
		<LoginModalFrame setIsLoginModal={setIsLoginMoal}>
			<LoginForm onSubmit={handleSubmit(onSubmit)}>
				<AuthInput
					placeholder="아이디를 입력해주세요."
					mode="login"
					{...register('username', {
						required: true,
					})}
				/>
				<AuthInput
					className="password"
					placeholder="비밀번호를 입력해주세요."
					mode="login"
					type="password"
					{...register('password', {
						required: true,
					})}
					errorMessage={loginError ? '로그인 정보를 다시 확인해주세요.' : ''}
				/>
				<LoginOption>
					<span>자동로그인</span>
					<span>비밀번호 찾기</span>
				</LoginOption>
				<BasicButton className="login-button">로그인</BasicButton>
			</LoginForm>
			<SubContainer>
				<hr />
				<span>회원이 아니신가요?</span>
				<BasicButton
					className="login-button"
					mode="login"
					onClick={() => {
						setIsLoginMoal(false);
						navigate('/signup');
					}}
				>
					회원가입
				</BasicButton>
			</SubContainer>
			<SubContainer>
				<hr />
				<span>간편로그인</span>
				<SocialButtons>
					<SocialButton mode="naver" />
					<SocialButton mode="kakao" />
					<SocialButton mode="google" />
				</SocialButtons>
			</SubContainer>
		</LoginModalFrame>
	);
};

export default LoginModal;

const LoginForm = styled.form`
	width: 360px;
	margin-top: 20px;

	& input {
		height: 60px;
		margin-top: 8px;
		margin-bottom: 6px;
	}

	& .password ~ p {
		height: 17px;
	}
`;

const LoginOption = styled.div`
	display: flex;
	justify-content: space-between;
	font-size: 12px;
	margin-top: 8px;
	margin-bottom: 16px;

	& span {
		cursor: pointer;
	}
`;

const SubContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;

	& span {
		padding: 0 20px;
		overflow: hidden;
		font-size: 12px;
		color: #a3a3a3;
		text-align: center;
		position: relative;
		z-index: 25;
		background-color: white;
		margin-bottom: 16px;
	}

	& hr {
		position: relative;
		bottom: -9px;
		display: block;
		margin: 0;
		width: 100%;
		height: 1px;
		background-color: #dbdbdb;
		border: none;
	}
`;

const SocialButtons = styled.div`
	display: flex;
	align-items: center;
	justify-content: center;

	& button:nth-child(2),
	button:nth-child(3) {
		margin-left: 24px;
	}
`;
