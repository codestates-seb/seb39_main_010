import React from 'react';
import styled from 'styled-components';

interface Prop {
	setState: React.Dispatch<React.SetStateAction<number | undefined>>;
	tag?: number;
}

interface questionTag {
	value: number;
	name: string;
}

export const questionTagOptions = [
	{ value: 1, name: '자기소개' },
	{ value: 2, name: '지원동기' },
	{ value: 3, name: '직무지식' },
	{ value: 4, name: '회사지식' },
	{ value: 5, name: '미래계획' },
	{ value: 6, name: '상황대처' },
	{ value: 7, name: '마지막 한마다' },
	{ value: 8, name: '기타' },
];

const QuestionTagSelect = ({ setState, tag }: Prop) => {
	const onQuestionTagChange = (event: React.FormEvent<HTMLSelectElement>) => {
		setState(+event.currentTarget.value);
	};

	return (
		<SortingContainer>
			<form onFocus={() => SortingContainer}>
				<select required onChange={onQuestionTagChange}>
					<option value="" disabled defaultValue={tag ? tag : ''}>
						질문 유형 선택
					</option>
					{questionTagOptions.map((option: questionTag) => {
						return (
							<option key={option.value} value={option.value}>
								{option.name}
							</option>
						);
					})}
				</select>
			</form>
		</SortingContainer>
	);
};
export default QuestionTagSelect;

export const SortingContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 10px 20px;

	width: 100%;
	height: 66px;

	border: 1px solid #d2d5da;
	border-radius: 10px;

	form {
		display: flex;
		justify-content: space-between;
		align-items: center;
		width: 100%;

		select {
			width: 100%;
			padding: 0;

			background: none;
			border: none;
			outline: none;
			font-size: 18px;
			line-height: 40px;

			option[value=''][disabled] {
				display: none;
			}
		}
	}
	:focus {
		outline: none !important;
		border-color: #94caf3;
		box-shadow: 0 0 0px 4px #d6edfc;
	}
`;
