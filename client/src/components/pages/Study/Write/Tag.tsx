import React from 'react';
import { useState } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { FiX } from 'react-icons/fi';

export const TagsInput = styled.div`
	display: flex;
	align-items: center;
	height: 66px;
	width: 576px;
	overflow: hidden;

	padding: 10px 20px;
	border: 1px solid ${theme.colors.gray300};
	border-radius: 10px;

	font-size: 18px;

	> ul {
		display: flex;
		padding: 0;
		margin: 8px 0 0 0;

		> .tag {
			width: auto;
			height: 32px;
			display: flex;
			align-items: center;
			justify-content: center;
			color: black;
			padding: 0 8px;
			font-size: 14px;
			list-style: none;
			border-radius: 6px;
			margin: 0 8px 8px 0;
			background: #d6edfc;
			> .tag-close-icon {
				display: flex;
				align-items: center;
				justify-content: center;
				width: 16px;
				height: 16px;
				line-height: 16px;
				text-align: center;
				font-size: 18px;
				margin-left: 8px;
				color: black;
				border-radius: 50%;
				background: #fff;
				cursor: pointer;
			}
		}
	}

	> input {
		flex: 1;
		border: none;
		height: 46px;
		font-size: 18px;
		padding: 4px 0 0 0;
		:focus {
			outline: transparent;
		}
	}

	&:focus-within {
		outline: none !important;
		border-color: #94caf3;
		box-shadow: 0 0 0px 4px #d6edfc;
	}
`;

export const Tag = () => {
	// const selectedTags = (tags) => console.log(tags);
	const initialTags = ['임시태그'];

	const [tags, setTags] = useState(initialTags);
	const removeTags = (indexToRemove: number) => {
		setTags(tags.filter((_, index) => index !== indexToRemove));
	};

	const addTags = (event: React.KeyboardEvent<HTMLInputElement>) => {
		const filtered = tags.filter((el) => el === event.currentTarget.value);
		if (event.currentTarget.value !== '' && filtered.length === 0) {
			setTags([...tags, event.currentTarget.value]);
			// selectedTags([...tags, event.target.value]);
			event.currentTarget.value = '';
		}
	};

	return (
		<>
			<TagsInput>
				<ul id="tags">
					{tags.map((tag, index) => (
						<li key={index} className="tag">
							<span className="tag-title">{tag}</span>
							<span
								className="tag-close-icon"
								onClick={() => removeTags(index)}
							>
								<FiX />
							</span>
						</li>
					))}
				</ul>
				<input
					className="tag-input"
					type="text"
					maxLength={10}
					onKeyUp={(event) => (event.key === 'Enter' ? addTags(event) : null)}
					placeholder="태그를 설정해 주세요 (10자이내, 최대5개)"
				/>
			</TagsInput>
		</>
	);
};
