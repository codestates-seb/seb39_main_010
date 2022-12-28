import React from 'react';
import styled from 'styled-components';
import Comment from './Comment';
import {
	Question,
	QuestionComment,
} from 'components/pages/Question/QuestionCard';
import { AxiosResponse } from 'axios';

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

export interface DefaultCommentProps {
	type: string;
	id?: string;
	setData: React.Dispatch<React.SetStateAction<Question | undefined>>;
	// eslint-disable-next-line
	getDataApi(id?: string | undefined): Promise<AxiosResponse<any, any>>;
}

interface CommentsProps extends DefaultCommentProps {
	comments: QuestionComment[];
}

const Comments = ({
	type,
	comments,
	id,
	setData,
	getDataApi,
}: CommentsProps) => {
	return (
		<CommentsContainer>
			{comments.length ? (
				comments.map((el) => (
					<Comment
						setData={setData}
						getDataApi={getDataApi}
						type={type}
						key={el.answerId}
						answer={el}
						id={id}
					/>
				))
			) : (
				<div>작성된 댓글이 없습니다.</div>
			)}
		</CommentsContainer>
	);
};

export default Comments;
