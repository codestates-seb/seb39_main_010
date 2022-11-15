import React, { useRef } from 'react';
import styled from 'styled-components';
import { useSetRecoilState } from 'recoil';
import { profileModalAtom, userAtom } from 'recoil/atom';
import { cookie } from 'utils/cookie';

const ProfileModal = () => {
	const setProfileModal = useSetRecoilState(profileModalAtom);
	const containerRef = useRef<HTMLDivElement>(null);
	const setUserInfo = useSetRecoilState(userAtom);

	const modalCloseHandler = (event: React.MouseEvent<HTMLDivElement>) => {
		if (containerRef.current === event.target) setProfileModal(false);
	};

	return (
		<ProfileModalContainer ref={containerRef} onClick={modalCloseHandler}>
			<Modal
				onClick={() => {
					setUserInfo(null);
					cookie.removeItem('refreshToken');
					cookie.removeItem('accessToken');
					window.location.replace('/');
				}}
			>
				로그아웃
			</Modal>
		</ProfileModalContainer>
	);
};

export default ProfileModal;

const ProfileModalContainer = styled.div`
	position: fixed;
	left: 1795px;
	top: 60px;
	width: 100%;
	height: 100%;
	z-index: 10;
`;

const Modal = styled.div`
	width: 200px;
	height: 48px;
	border-radius: 20px;
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	background-color: white;
	padding: 27px 70px;
	box-shadow: 0 5px 25px rgb(0 0 0 / 15%);
	font-weight: 600;
	cursor: pointer;
`;
