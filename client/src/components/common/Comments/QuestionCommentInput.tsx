import React, { ChangeEvent, useState } from 'react';
import styled from 'styled-components';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { RiSendPlaneFill } from 'react-icons/ri';
import { postCommentApi } from 'apis/authApiClient';
import { Question } from '../../pages/Question/QuestionCard';
import { AxiosResponse } from 'axios';
import { getUser } from 'utils/user';

interface CommentInputProps {
	id?: string;
	type: string;
	setData: React.Dispatch<React.SetStateAction<Question | undefined>>;
	// eslint-disable-next-line
	getDataApi(id?: string | undefined): Promise<AxiosResponse<any, any>>;
}

const CommentInput = ({ id, type, getDataApi, setData }: CommentInputProps) => {
	const isLoggedIn = getUser();
	const [comment, setComment] = useState('');

	const addComment = () => {
		postCommentApi(type, comment, id)
			.then(() => getDataApi(id))
			.then((res) => {
				setData(res.data);
				setComment('');
			});
	};

	const handleFormClick = () => {
		if (!isLoggedIn) {
			window.alert('로그인 후 이용 가능한 서비스입니다.');
		}
	};

	return (
		<CommentsContainer>
			<div className="commentsinput">
				<AvatarImg className="avatar-svg" />
				<div>
					<form onClick={handleFormClick}>
						<input
							value={comment}
							onChange={(e: ChangeEvent<HTMLInputElement>) =>
								setComment(e.currentTarget.value)
							}
							type="text"
							placeholder="댓글을 남겨 보세요"
							disabled={isLoggedIn ? false : true}
						/>
						<RiSendPlaneFill size={25} color="#666666" onClick={addComment} />
					</form>
				</div>
			</div>
		</CommentsContainer>
	);
};

export default CommentInput;

const CommentsContainer = styled.div`
	width: 100%;

	.avatar-svg {
		width: 80px;
		height: 80px;
		margin-right: 28px;
	}

	.commentsinput {
		display: flex;
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
