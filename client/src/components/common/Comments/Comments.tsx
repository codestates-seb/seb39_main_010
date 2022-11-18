import React from 'react';
import styled from 'styled-components';
import { RiSendPlaneFill } from 'react-icons/ri';
import Comment from './Comment';
import CommentIntro from './CommentIntro';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';

const CommentsContainer = styled.div`
	/* width: 1100px; */
	width: 100%;

	.avatar-svg {
		width: 80px;
		height: 80px;
		margin-right: 28px;
	}

	.commentsinput {
		display: flex;
		/* justify-content: space-between; */
		align-items: center;
		margin: 40px 0;

		div:nth-child(2) {
			display: flex;
			align-items: center;
			padding: 0px 36px;

			width: 100%;
			height: 80px;

			background: #f5f6f8;

			border-radius: 39px;
			font-size: 18px;

			form {
				display: flex;
				justify-content: space-between;
				align-items: center;

				width: 100%;

				input {
					width: 100%;

					background: none;
					border: none;
					outline: none;
					font-size: 18px;
				}
			}
		}
	}
`;

const Comments = () => {
	return (
		<CommentsContainer>
			<CommentIntro
				title={`함께 스터디를 하고 싶다면 이야기를 나누어 보세요.`}
				content={`비방글 혹은 글 내용과 상관없는 댓글을 작성할 시 삭제될 수 있습니다.`}
			/>
			<div className="commentsinput">
				<AvatarImg className="avatar-svg" />
				<div>
					<form>
						<input type="text" placeholder="댓글을 남겨 보세요" />
						<RiSendPlaneFill size={25} color="#666666" />
					</form>
				</div>
			</div>
			<Comment />
			<Comment />
			<Comment />
		</CommentsContainer>
	);
};

export default Comments;
