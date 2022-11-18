import React from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import AuthInput from 'components/AuthInput/AuthInput';
import BasicButton from 'components/common/BasicButton/BasicButton';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { emailAuthenticationApi, signupApi } from 'apis/apiClient';
import { ReactComponent as LogoImg } from 'assets/images/logo.svg';
import SocialButton from 'components/common/SocialButtons/SocialButtons';
import { SignupSubmitForm } from 'types';
import { errorMessage, regex } from 'utils/signupValidation';

interface SignupForm extends SignupSubmitForm {
	passwordConfirm: string;
}

const Signup = () => {
	const {
		register,
		watch,
		getValues,
		handleSubmit,
		formState: { errors },
	} = useForm<SignupForm>();

	const onSubmit: SubmitHandler<SignupSubmitForm> = async (data) => {
		const { email, username, password, nickname } = data;
		signupApi({ email, username, password, nickname });
	};

	const handleSocialButtonClick = (type: string) => {
		window.location.href = `${process.env.REACT_APP_BASE_URL}/oauth2/authorization/${type}?redirect_uri=http://localhost:3000/login/oauth`;
	};

	return (
		<Container>
			<FormContainer>
				<LogoImg className="logo" />
				<SocialSignup>
					<span>SNS계정으로 간편 가입</span>
					<SocialButtons>
						<SocialButton
							mode="naver"
							className="svg"
							onClick={() => handleSocialButtonClick('naver')}
						/>
						<SocialButton
							mode="kakao"
							className="kakao"
							onClick={() => handleSocialButtonClick('kakao')}
						/>
						<SocialButton
							mode="google"
							className="svg"
							onClick={() => handleSocialButtonClick('google')}
						/>
					</SocialButtons>
				</SocialSignup>
				<SignupForm onSubmit={handleSubmit(onSubmit)}>
					<AuthInput
						placeholder="example@wely.com"
						label="이메일"
						type="email"
						{...register('email', {
							required: true,
							pattern: regex.email,
						})}
						errorMessage={errors.email && errorMessage.email}
					/>
					<BasicButton
						className="email-button"
						type="button"
						mode={'login'}
						disabled={!regex.email.test(watch('email'))}
						onClick={() => emailAuthenticationApi(getValues('email'))}
					>
						이메일 인증하기
					</BasicButton>
					<AuthInput
						placeholder="4~20자 이내, 영문, 숫자 사용 가능"
						label="아이디"
						{...register('username', {
							required: true,
							pattern: regex.username,
						})}
						errorMessage={errors.username && errorMessage.username}
					/>
					<AuthInput
						placeholder="8~20자 이내, 영문 대소문자, 숫자, 특수문자"
						label="비밀번호"
						type="password"
						{...register('password', {
							required: true,
							pattern: regex.password,
						})}
						errorMessage={errors.password && errorMessage.password}
					/>
					<AuthInput
						placeholder="비밀번호 확인"
						label="비밀번호 확인"
						type="password"
						{...register('passwordConfirm', {
							required: true,
							validate: (value: string) => {
								if (watch('password') !== value) {
									return '비밀번호가 일치하지 않습니다.';
								}
							},
						})}
						errorMessage={
							errors.passwordConfirm && errorMessage.passwordConfirm
						}
					/>
					<AuthInput
						placeholder="2~20자 이내, 한글, 영문, 숫자 사용 가능"
						label="닉네임"
						{...register('nickname', {
							required: true,
							pattern: regex.nickname,
						})}
						errorMessage={errors.nickname && errorMessage.nickname}
					/>
					<BasicButton>가입하기</BasicButton>
				</SignupForm>
			</FormContainer>
		</Container>
	);
};

export default Signup;

const Container = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
`;

const FormContainer = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	min-width: 400px;
	margin-top: 2.5rem;

	& .logo {
		width: 200px;
		height: 64.42px;
	}
`;

const SocialSignup = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	padding: 18px 0;
	margin-top: 5px;
	margin-bottom: 23px;
	border-bottom: 1px solid ${theme.colors.gray200};
	color: #585858;
`;

const SocialButtons = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;

	& button {
		width: 50px;
		height: 50px;
	}

	& .kakao {
		margin: 0 20px;
	}
`;

const SignupForm = styled.form`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;

	& input {
		height: 50px;
		margin-bottom: 4px;
	}

	& input:focus {
		outline: none !important;
		border-color: #94caf3;
		box-shadow: 0 0 0px 4px #d6edfc;
	}

	& p {
		height: 17px;
		margin-bottom: 9px;
	}

	& button {
		height: 60px;
		margin: 20px;
	}

	& .email-button {
		margin-top: 0;
		margin-bottom: 30px;
		height: 50px;
		font-weight: 400;
		font-size: 18px;
	}
`;
