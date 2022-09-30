import React from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import AuthInput from 'components/AuthInput/AuthInput';
import BasicButton from 'components/common/BasicButton/BasicButton';
import styled from 'styled-components';
import { theme } from 'styles/theme';

interface FormData {
	email: string;
	id: string;
	password: string;
	passwordConfirm: string;
	nickname: string;
}

const Signup = () => {
	const {
		register,
		watch,
		getValues,
		handleSubmit,
		formState: { errors },
	} = useForm<FormData>();

	const onSubmit: SubmitHandler<FormData> = (data) => {
		console.log(data);
	};

	const regex = {
		email:
			/^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*.[A-Za-z]{2,3}$/i,
		id: /^[A-Za-z0-9]{6,12}$/,
		password: /^[A-Za-z0-9]{8,30}$/,
		nickname: /^[A-Za-z0-9가-힣]{2,20}$/,
	};

	const errorMessage = {
		email: '이메일 형식을 확인해주세요.',
		id: '아이디는 6~12자 이내의 영문, 숫자로 입력해주세요.',
		password: '비밀번호는 8~30자 이내의 영문, 숫자로 입력해주세요.',
		passwordConfirm: '입력하신 비밀번호와 일치하지 않습니다.',
		nickname: '닉네임은 2~20자 이내의 알파벳, 한글, 숫자로 입력해주세요.',
	};

	return (
		<Container>
			<FormContainer>
				<span className="logo">WEPLY</span>
				<SocialSignup>
					<span>SNS계정으로 간편 가입</span>
					<SocialButtons>
						<div>N</div>
						<div>G</div>
						<div>K</div>
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
						mode={'login'}
						disabled={!regex.email.test(watch('email'))}
						onClick={() => console.log('getValues', getValues('email'))}
					>
						이메일 인증하기
					</BasicButton>
					<AuthInput
						placeholder="6~12자 이내 영문, 숫자 사용 가능"
						label="아이디"
						{...register('id', {
							required: true,
							pattern: regex.id,
						})}
						errorMessage={errors.id && errorMessage.id}
					/>
					<AuthInput
						placeholder="영문 소문자, 숫자 조합 8자 이상의 비밀번호"
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
						placeholder="알파벳, 한글, 숫자를 20자 이하로 입력해주세요."
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

	& .logo {
		height: 78px;
		font-size: 60px;
		font-weight: 700;
		padding: 0;
		color: ${theme.colors.blueMain};
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

	& div {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 50px;
		height: 50px;
		border: none;
		border-radius: 50%;
		background-color: ${theme.colors.gray200};
		margin-left: 20px;
	}
`;

const SignupForm = styled.form`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;

	& label {
		margin-bottom: 12px;
	}

	& input {
		font-size: 16px;
		height: 50px;
		margin-bottom: 4px;
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
