import React, { useState } from 'react';
import { BasicButton } from 'components/common';
import { BsPencil, BsXLg, BsCheckLg } from 'react-icons/bs';
import { BiBuildings } from 'react-icons/bi';
import { IntroductionContainer, ProfileImg, ProfileText } from './style';
import { UserInfo } from 'pages/Mypage/Mypage';
import {
	patchCompanyInfo,
	patchIntroductionInfo,
	patchUserInfo,
} from 'apis/authApiClient';

interface IntroductionProps {
	userInfo: UserInfo;
	id?: number;
	setUserInfo: React.Dispatch<React.SetStateAction<UserInfo | undefined>>;
}

const Introduction = ({ userInfo, id, setUserInfo }: IntroductionProps) => {
	const [isEdit, setIsEdit] = useState(false);
	const [favoriteCompany, setFavoriteCompany] = useState(
		userInfo.favoriteCompany || null
	);
	const [selfIntroductions, setSelfIntroductions] = useState(
		userInfo.selfIntroductions || null
	);

	const handleEditButtonClick = () => {
		setIsEdit(!isEdit);
	};

	const handleCompanyChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setFavoriteCompany(e.target.value);
	};

	const handleIntroductionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSelfIntroductions(e.target.value);
	};

	const handleUserInfoSubmit = () => {
		if (
			userInfo.favoriteCompany === favoriteCompany &&
			userInfo.selfIntroductions === selfIntroductions
		) {
			if (window.confirm('수정을 취소하시겠습니까?')) {
				return setIsEdit(false);
			}
		}

		if (
			userInfo.favoriteCompany !== favoriteCompany &&
			userInfo.selfIntroductions !== selfIntroductions
		) {
			patchUserInfo(favoriteCompany, selfIntroductions, id).then((res) => {
				setUserInfo(res);
				setIsEdit(!isEdit);
			});

			return;
		} else if (userInfo.favoriteCompany !== favoriteCompany) {
			patchCompanyInfo(favoriteCompany, id).then((res) => {
				setUserInfo(res);
				setIsEdit(!isEdit);
			});

			return;
		} else if (userInfo.selfIntroductions !== selfIntroductions) {
			patchIntroductionInfo(selfIntroductions, id).then((res) => {
				setUserInfo(res);
				setIsEdit(!isEdit);
			});
		}
	};

	return (
		<IntroductionContainer>
			<ProfileImg>사진</ProfileImg>
			<ProfileText>
				<div className="nicknamecompany">
					<div id="nickname">{userInfo.nickname}</div>
					<div className="buttons">
						{!isEdit ? (
							<>
								<BasicButton>
									<span>비밀번호 변경</span>
								</BasicButton>
								<BasicButton onClick={handleEditButtonClick}>
									<BsPencil size={15} />
									<span>수정</span>
								</BasicButton>
							</>
						) : (
							<>
								<BasicButton
									onClick={() => {
										if (window.confirm('수정을 취소하시겠습니까?'))
											return setIsEdit(false);
									}}
								>
									<BsXLg size={15} />
									<span>수정 취소</span>
								</BasicButton>
								<BasicButton onClick={handleUserInfoSubmit}>
									<BsCheckLg size={15} />
									<span>수정 완료</span>
								</BasicButton>
							</>
						)}
					</div>
				</div>
				<div id="company">
					<BiBuildings />
					<span>관심회사</span>
					{isEdit ? (
						<input
							value={favoriteCompany || ''}
							onChange={handleCompanyChange}
							placeholder={'관심 회사를 입력해주세요.'}
						/>
					) : (
						<span>
							{userInfo.favoriteCompany || '관심 회사를 입력해주세요.'}
						</span>
					)}
				</div>

				{isEdit ? (
					<input
						value={selfIntroductions || ''}
						onChange={handleIntroductionChange}
						placeholder={'자기소개를 입력해주세요.'}
					/>
				) : (
					<div className="text">
						<span>
							{userInfo.selfIntroductions || '자기소개를 입력해주세요.'}
						</span>
					</div>
				)}
			</ProfileText>
		</IntroductionContainer>
	);
};

export default Introduction;
