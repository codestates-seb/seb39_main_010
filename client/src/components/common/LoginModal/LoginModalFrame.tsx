import React, { useRef } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { IoClose } from 'react-icons/io5';
import { ReactComponent as LogoImg } from 'assets/images/logo.svg';
import { SetterOrUpdater } from 'recoil';
import { useNavigate } from 'react-router-dom';

interface LoginModalFrameProps {
	children: React.ReactNode;
	setIsLoginModal: SetterOrUpdater<boolean>;
	mode?: string;
}

const LoginModalFrame = ({
	children,
	setIsLoginModal,
	mode,
}: LoginModalFrameProps) => {
	const containerRef = useRef<HTMLDivElement>(null);
	const navigate = useNavigate();

	const modalCloseHandler = (event: React.MouseEvent<HTMLDivElement>) => {
		if (containerRef.current === event.target) {
			if (mode) navigate(-1);
			setIsLoginModal(false);
		}
	};

	return (
		<LoginModalContainer ref={containerRef} onClick={modalCloseHandler}>
			<Modal>
				<button
					className="close-button"
					type="button"
					onClick={() => {
						if (mode) navigate(-1);
						setIsLoginModal(false);
					}}
				>
					<IoClose />
				</button>
				<LogoImg className="logo" />
				{children}
			</Modal>
		</LoginModalContainer>
	);
};

export default LoginModalFrame;

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
	border-radius: 20px;
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	background-color: white;
	padding: 45px 70px;

	& .close-button {
		position: absolute;
		margin: 0;
		padding: 0;
		background-color: transparent;
		border: none;
		top: 17px;
		right: 25px;
		font-size: 38px;
		color: ${theme.colors.gray500};
	}

	& .logo {
		width: 120px;
		height: 38.5px;
	}

	& .login-button {
		height: 60px;
		font-size: 18px;
		margin-bottom: 16px;
	}
`;
