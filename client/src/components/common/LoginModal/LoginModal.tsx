import React, { useRef } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { IoClose } from 'react-icons/io5';
import { useSetRecoilState } from 'recoil';
import { loginModalAtom } from 'recoil/atom';
import AuthInput from 'components/AuthInput/AuthInput';
import BasicButton from '../BasicButton/BasicButton';
import SocialButton from '../SocialButtons/SocialButtons';
import { useNavigate } from 'react-router-dom';

const LoginModal = () => {
	const setIsLoginMoal = useSetRecoilState(loginModalAtom);
	const containerRef = useRef<HTMLDivElement>(null);
	const navigate = useNavigate();

	const modalCloseHandler = (event: React.MouseEvent<HTMLDivElement>) => {
		if (containerRef.current === event.target) setIsLoginMoal(false);
	};

	return (
		<LoginModalContainer ref={containerRef} onClick={modalCloseHandler}>
			<Modal>
				<button
					className="close-button"
					type="button"
					onClick={() => setIsLoginMoal(false)}
				>
					<IoClose />
				</button>
				<div className="logo">WEPLY</div>
				<LoginForm>
					<AuthInput placeholder="아이디를 입력해주세요." mode="login" />
					<AuthInput
						className="password"
						placeholder="비밀번호를 입력해주세요."
						mode="login"
						type="password"
						// errorMessage="로그인 정보를 다시 확인해주세요."
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
			</Modal>
		</LoginModalContainer>
	);
};

export default LoginModal;

const LoginModalContainer = styled.div`
	position: fixed;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	z-index: 10;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: rgba(0, 0, 0, 0.5);
`;

const Modal = styled.div`
	width: 500px;
	height: 680px;
	border-radius: 20px;
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	background-color: white;
	padding: 27px 70px;

	& .close-button {
		position: absolute;
		margin: 0;
		padding: 0;
		background-color: transparent;
		border: none;
		top: 17px;
		right: 25px;
		font-size: 38px;
		/* x 버튼 색 #666666 -> gray500으로 적용해도 될지 확인 */
		color: #666666;
	}

	& .logo {
		display: flex;
		justify-content: center;
		align-items: center;
		color: ${theme.colors.blueMain};
		width: 205px;
		height: 50px;
		border: 1px solid ${theme.colors.blueMain};
	}

	& .login-button {
		height: 60px;
		font-size: 18px;
		margin-bottom: 16px;
	}
`;

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
