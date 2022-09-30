import React from 'react';
import { BasicButton } from 'components/common';
import { BsPencil } from 'react-icons/bs';
import { BiBuildings } from 'react-icons/bi';
import {
	Empty,
	IntroductionContainer,
	Profile,
	ProfileImg,
	ProfileText,
} from './style';

const Introduction = () => {
	return (
		<IntroductionContainer>
			<Empty />
			<Profile>
				<ProfileImg>사진</ProfileImg>
				<ProfileText>
					<div className="nicknamecompany">
						<div id="nickname">닉네임</div>
						<BasicButton>
							<BsPencil size={15} />
							수정
						</BasicButton>
					</div>
					<div id="company">
						<BiBuildings />
						<span>관심회사</span>
						<span>회사이름</span>
					</div>
					<div className="text">자기소개 텍스트 영역</div>
				</ProfileText>
			</Profile>
		</IntroductionContainer>
	);
};

export default Introduction;
