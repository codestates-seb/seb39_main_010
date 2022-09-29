import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { HeaderContainer, LogoandNav, MypageImg } from './style';
import { FiUser } from 'react-icons/fi';
import { idText } from 'typescript';

const Header = () => {
	const [tab, setTab] = useState('');

	const navigate = useNavigate();
	const getclick = (e: React.FormEvent) => {
		setTab(e.currentTarget.id);
		console.log(tab, '나야');
	};
	return (
		<HeaderContainer>
			<LogoandNav>
				<div onClick={() => navigate('/')}></div>
				<ul>
					<li
						id="question"
						onClick={() => {
							navigate('/');
							getclick;
						}}
						className={`${tab ? 'active' : ''}`}
					>
						면접 질문
					</li>
					<li
						id="study"
						onClick={() => {
							navigate('/study');
							getclick;
						}}
						className={`${tab ? 'active' : ''}`}
					>
						스터디모집
					</li>
					<li
						id="review"
						onClick={() => {
							navigate('/interview/review');
							getclick;
						}}
						className={`${tab ? 'active' : ''}`}
					>
						면접 후기
					</li>
				</ul>
			</LogoandNav>

			<MypageImg
				onClick={() => {
					navigate('/mypage');
				}}
			>
				<FiUser size={35} />
			</MypageImg>
		</HeaderContainer>
	);
};
export default Header;
