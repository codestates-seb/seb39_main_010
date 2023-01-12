import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { userAtom } from 'recoil/atom';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { cookie } from 'utils/cookie';

const ProfileModal = () => {
	const navigate = useNavigate();
	const user = useRecoilValue(userAtom);

	return (
		<ProfileModalContainer>
			<Modal onClick={() => navigate(`/profile/${user?.memberId}`)}>
				내 프로필
			</Modal>
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
	box-shadow: 0 5px 25px rgb(0 0 0 / 15%);
	border-radius: 15px;
	overflow: hidden;

	div:hover {
		background-color: ${theme.colors.gray100};
	}
`;

const Modal = styled.div`
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	background-color: white;
	padding: 20px 50px;
	font-weight: 600;
	cursor: pointer;
`;
