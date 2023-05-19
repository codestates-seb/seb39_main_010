import React from 'react';
import styled from 'styled-components';

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

interface JobOptions {
	value: number;
	name: string;
}

interface Prop {
	setState: React.Dispatch<React.SetStateAction<number | undefined>>;
	category?: number;
}

export interface JobOption {
	value: number;
	name: string;
}

export const jobOptions: JobOption[] = [
	{ value: 1, name: '영업/고객상담' },
	{ value: 2, name: '경영/사무' },
	{ value: 3, name: '마케팅/광고/홍보' },
	{ value: 4, name: '생산/제조' },
	{ value: 5, name: '연구개발/설계' },
	{ value: 6, name: 'IT/인터넷' },
	{ value: 7, name: '서비스' },
	{ value: 8, name: '무역/유통' },
	{ value: 9, name: '의료' },
	{ value: 10, name: '건설' },
	{ value: 11, name: '교육' },
	{ value: 12, name: '디자인' },
	{ value: 13, name: '전문/특수직' },
	{ value: 14, name: '미디어' },
];

const JobSelect = ({ setState, category }: Prop) => {
	const onJobSelectChange = (event: React.FormEvent<HTMLSelectElement>) => {
		setState(parseInt(event.currentTarget.value) || undefined);
	};

	return (
		<SortingContainer>
			<form onFocus={() => SortingContainer}>
				<select
					required
					onChange={onJobSelectChange}
					defaultValue={category || undefined}
				>
					<option value="">직무 카테고리 선택</option>
					{jobOptions.map((option: JobOptions) => {
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
export default JobSelect;
