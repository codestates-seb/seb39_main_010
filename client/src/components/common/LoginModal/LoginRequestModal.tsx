import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { loginModalAtom, loginRequestModalAtom } from 'recoil/atom';
import styled from 'styled-components';
import BasicButton from '../BasicButton/BasicButton';
import LoginModalFrame from './LoginModalFrame';

const LoginRequestModal = () => {
	const navigate = useNavigate();
	const setIsLoginRequestModal = useSetRecoilState(loginRequestModalAtom);
	const setIsLoginModal = useSetRecoilState(loginModalAtom);
	return (
		<LoginModalFrame setIsLoginModal={setIsLoginRequestModal} mode={'request'}>
			<MessageContainer>
				<div>로그인이 필요한 서비스입니다.</div>
				<div>로그인 하시겠습니까?</div>
			</MessageContainer>
			<ButtonContainer>
				<BasicButton
					onClick={() => {
						setIsLoginRequestModal(false);
						navigate(-1);
						setIsLoginModal(true);
					}}
				>
					로그인
				</BasicButton>
				<BasicButton
					mode={'login'}
					onClick={() => {
						navigate(-1);
						setIsLoginRequestModal(false);
					}}
				>
					뒤로 가기
				</BasicButton>
			</ButtonContainer>
		</LoginModalFrame>
	);
};

export default LoginRequestModal;

const MessageContainer = styled.div`
	margin: 24px 0;
	display: flex;
	align-items: center;
	flex-direction: column;
	font-size: 24px;
	width: 100%;
	gap: 12px;
`;

const ButtonContainer = styled.div`
	display: flex;
	justify-content: center;
	width: 100%;
	gap: 24px;
`;
