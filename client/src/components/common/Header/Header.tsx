import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import { HeaderContainer, LogoandNav } from './style';
import { useRecoilState, useRecoilValue } from 'recoil';
import { loginModalAtom, loginRequestModalAtom } from 'recoil/atom';
import LoginModal from '../LoginModal/LoginModal';
import { ReactComponent as LogoImg } from 'assets/images/logo.svg';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { cookie } from 'utils/cookie';
import ProfileModal from '../ProfileModal/ProfileModal';
import LoginRequestModal from '../LoginModal/LoginRequestModal';
import useModal from 'hooks/useModal';

const Header = () => {
	const [isLoginModal, setIsLoginMoal] = useRecoilState(loginModalAtom);
	const isLoginRequestModal = useRecoilValue(loginRequestModalAtom);
	const { isModal, ref, handleModalChange } = useModal();

	const menus = [
		{ id: 0, menu: '면접 질문', url: '/interview/question' },
		{ id: 1, menu: '스터디모집', url: '/study' },
		{ id: 2, menu: '면접 후기', url: '/interview/review' },
	];

	return (
		<HeaderContainer>
			{isLoginModal && <LoginModal />}
			{isLoginRequestModal && <LoginRequestModal />}
			{isModal && <ProfileModal />}
			<LogoandNav>
				<Link to="/">
					<LogoImg />
				</Link>
				<ul>
					{menus.map((menu) => {
						return (
							<li key={menu.id}>
								<NavLink
									to={menu.url}
									className={({ isActive }) =>
										isActive ? 'active' : undefined
									}
								>
									{menu.menu}
								</NavLink>
							</li>
						);
					})}
				</ul>
			</LogoandNav>
			{cookie.getItem('refreshToken') ? (
				<div ref={ref}>
					<AvatarImg
						className="avatar-svg"
						onClick={() => handleModalChange()}
					/>
				</div>
			) : (
				<span className="login" onClick={() => setIsLoginMoal(!isLoginModal)}>
					로그인
				</span>
			)}
		</HeaderContainer>
	);
};
export default Header;
