import React from 'react';
import styled from 'styled-components';
import { AiOutlineEye } from 'react-icons/ai';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';

const TitleContainer = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	padding: 35px 0 20px 0;

	width: 1100px;
	height: 210px;

	.avatar-svg {
		width: 50px;
		height: 50px;
	}

	.title {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;

		h2 {
			margin-top: 10px;

			font-weight: 600;
			font-size: 32px;
		}
	}

	.detail {
		display: flex;
		justify-content: space-between;

		width: 100%;

		font-size: 18px;

		span {
			display: flex;
			align-items: center;

			margin: 10px;
			color: #525252;
		}

		div {
			display: flex;
		}
	}
`;

const Title = () => {
	return (
		<TitleContainer>
			<div className="title">
				<div>#프론트엔드 #파이썬 #코딩 #프로젝트 #스터디</div>
				<h2>사이드 프로젝트 같이 성장 하실 리액트 네이티브 개발자 구합니다!</h2>
			</div>
			<div className="detail">
				<div>
					<AvatarImg className="avatar-svg" />
					<span>닉네임</span>
					<span>2일 전</span>
				</div>
				<div>
					<span>
						<AiOutlineEye size={25} />
						36
					</span>
					<span>신고</span>
				</div>
			</div>
		</TitleContainer>
	);
};

export default Title;
