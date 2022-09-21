import React from 'react';
import { useNavigate } from 'react-router-dom';
import { HeaderContainer, LogoandNav, MypageImg } from './style';
import { BsPersonCircle } from 'react-icons/bs';

const Header = () => {
	const navigate = useNavigate();

	return (
		<HeaderContainer>
			<LogoandNav>
				<div onClick={() => navigate('/')}></div>
				<ul>
					<li onClick={() => navigate('/')}>면접 질문</li>
					<li onClick={() => navigate('/study')}>스터디모집</li>
					<li onClick={() => navigate('/interview/review')}>면접 후기</li>
				</ul>
			</LogoandNav>

			<MypageImg>
				<BsPersonCircle size={50} />
			</MypageImg>
		</HeaderContainer>
	);
};
export default Header;
