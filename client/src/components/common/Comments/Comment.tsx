import React from 'react';
import styled from 'styled-components';
import { BsSuitHeart } from 'react-icons/bs';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';

const CommentContainer = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;

	margin-bottom: 25px;
	.nameandtext {
		display: flex;
		flex-direction: column;
		gap: 6px;

		width: 988px;

		div:nth-child(1) {
			font-weight: bold;
		}
		div:nth-child(2) {
			color: #4d4d4d;
		}

		.info {
			display: flex;
			flex-direction: row;
			align-items: center;
			gap: 8px;
			span {
				color: #4d4d4d;
			}
		}
	}

	.dot {
		width: 3px;
		height: 3px;

		border-radius: 100%;
		background-color: #d9d9d9;
	}
`;

const Comment = () => {
	return (
		<CommentContainer>
			<AvatarImg />
			<div className="nameandtext">
				<div>닉네임</div>
				<div>내용이 여기에 들어갑니다</div>
				<div className="info">
					<span>6시간 전</span>
					<div className="dot"></div>
					<span>
						<BsSuitHeart size={15} />
						좋아요
					</span>
					<div className="dot"></div>
					<span>답글달기</span>
					<div className="dot"></div>
					<span>신고</span>
				</div>
			</div>
		</CommentContainer>
	);
};

export default Comment;
