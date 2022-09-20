import React from 'react';
import { useNavigate } from 'react-router-dom';
import { HeaderContainer, LogoandNav, MypageImg } from './style';
import { BsPersonCircle } from 'react-icons/bs';

const Header = () => {
	const navigate = useNavigate();
	function handleClickHome() {
		navigate('/');
	}
	function handleClickStudy() {
		navigate('/study-board');
	}
	function handleClickReview() {
		navigate('/review');
	}
	return (
		<HeaderContainer>
			<LogoandNav>
				<div onClick={handleClickHome}></div>
				<ul>
					<li onClick={handleClickHome}>면접 질문</li>
					<li onClick={handleClickStudy}>스터디모집</li>
					<li onClick={handleClickReview}>면접 후기</li>
				</ul>
			</LogoandNav>

			<MypageImg>
				<BsPersonCircle size={50} />
			</MypageImg>
		</HeaderContainer>
	);
};
export default Header;
