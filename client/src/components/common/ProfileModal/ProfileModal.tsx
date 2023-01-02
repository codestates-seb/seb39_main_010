import React from 'react';
import styled from 'styled-components';
import { cookie } from 'utils/cookie';

const ProfileModal = () => {
	return (
		<ProfileModalContainer>
			<Modal
				onClick={() => {
					cookie.removeItem('refreshToken');
					localStorage.removeItem('accessToken');
					localStorage.removeItem('persistUserAtom');
				}}
			>
				로그아웃
			</Modal>
		</ProfileModalContainer>
	);
};

export default ProfileModal;

const ProfileModalContainer = styled.div`
	position: absolute;
	right: 6%;
	top: 75px;
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
