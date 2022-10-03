import React from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { ReactComponent as AvatarImg } from 'assets/images/avatar.svg';
import { AiOutlineEye } from 'react-icons/ai';
import { BiCommentDetail } from 'react-icons/bi';

interface QuestionComment {
	author: string;
}

interface QuestionData {
	id: number;
	category: string;
	tag: string;
	title: string;
	content: string;
	author: string;
	view: number;
	comments: QuestionComment[];
	createdAt: number;
}

interface Props {
	data: QuestionData;
}

const QuestionCard = ({ data }: Props) => {
	return (
		<CardContainer>
			<CategoryInfo>
				<div className="tag-box">
					<Tag className="category">#{data.category}</Tag>
					<Tag className="tag">#{data.tag}</Tag>
				</div>
				<span className="created-at">{data.createdAt}</span>
			</CategoryInfo>
			<Content>
				<h3>{data.title}</h3>
				<p>{data.content.slice(0, 43)}...</p>
			</Content>
			<AuthorInfo>
				<div className="author">
					<AvatarImg className="avatar-svg" />
					<span>{data.author}</span>
				</div>
				<div className="info-counts">
					<span className="view">
						<AiOutlineEye className="view-svg" />
						<span>{data.view}</span>
					</span>
					<span className="comment">
						<BiCommentDetail className="comment-svg" />
						<span>{data.comments.length}</span>
					</span>
				</div>
			</AuthorInfo>
		</CardContainer>
	);
};

export default QuestionCard;

const CardContainer = styled.div`
	width: 100%;
	height: 218px;
	border-radius: 1rem;
	border: 1px solid ${theme.colors.gray300};
	padding: 1rem 1.5rem;
	display: flex;
	justify-content: space-between;
	flex-direction: column;
`;

const CategoryInfo = styled.span`
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 12px;
	border-radius: 1.5rem;
	margin-bottom: 1rem;

	& .category {
		background-color: ${theme.colors.blue100};
	}

	& .tag {
		background-color: ${theme.colors.green100};
	}

	& .created-at {
		color: ${theme.colors.gray500};
	}
`;

const Tag = styled.div`
	display: inline-block;
	justify-content: center;
	align-items: center;
	padding: 5px 14px;
	border-radius: 1.5rem;
	margin-right: 10px;
	color: ${theme.colors.gray800};
`;

const Content = styled.div`
	margin-bottom: 12px;

	& h3 {
		font-size: 18px;
		color: ${theme.colors.gray900};
		margin-bottom: 8px;
	}

	& p {
		font-size: 1rem;
		color: ${theme.colors.gray500};
	}
`;

const AuthorInfo = styled.div`
	width: 100%;
	display: flex;
	justify-content: space-between;
	border-top: 1px solid ${theme.colors.gray300};
	padding-top: 10px;

	& .view,
	.comment {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-left: 18px;
	}

	& .info-counts {
		font-size: 18px;
		color: #6b7280;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	& .view-svg {
		width: 26px;
		height: 26px;
		margin-right: 6px;
	}

	& .comment-svg {
		width: 22px;
		height: 22px;
		margin-right: 6px;
	}

	& .avatar-svg {
		height: 30px;
		width: 30px;
		margin-right: 10px;
	}

	& .author {
		font-size: 14px;
		display: flex;
		justify-content: center;
		align-items: center;
		color: ${theme.colors.gray800};
	}
`;
