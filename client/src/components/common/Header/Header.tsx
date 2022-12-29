import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { HeaderContainer, LogoandNav } from './style';
import { useRecoilState } from 'recoil';
import { loginModalAtom, profileModalAtom } from 'recoil/atom';
import LoginModal from '../LoginModal/LoginModal';
import { ReactComponent as LogoImg } from 'assets/images/logo.svg';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { cookie } from 'utils/cookie';
import ProfileModal from '../ProfileModal/ProfileModal';

const Header = () => {
	const [isLoginModal, setIsLoginMoal] = useRecoilState(loginModalAtom);
	const [isProfileModal, setIsProfileModal] = useRecoilState(profileModalAtom);
	const navigate = useNavigate();

	const menus = [
		{ id: 0, menu: '면접 질문', url: '/interview/question' },
		{ id: 1, menu: '스터디모집', url: '/study' },
		{ id: 2, menu: '면접 후기', url: '/interview/review' },
	];

	// const ;

	return (
		<HeaderContainer>
			{isLoginModal && <LoginModal />}
			{isProfileModal && <ProfileModal />}
			<LogoandNav>
				<Link to="/">
					<LogoImg />
				</Link>
				<ul>
					{menus.map((menu) => {
						return (
							<li key={menu.id} onClick={() => navigate(menu.url)}>
								{menu.menu}
							</li>
						);
					})}
				</ul>
			</LogoandNav>
			{cookie.getItem('refreshToken') ? (
				<AvatarImg
					className="avatar-svg"
					onClick={() => setIsProfileModal(!isProfileModal)}
				/>
			) : (
				<span className="login" onClick={() => setIsLoginMoal(!isLoginModal)}>
					로그인
				</span>
			)}
		</HeaderContainer>
	);
};
export default Header;
