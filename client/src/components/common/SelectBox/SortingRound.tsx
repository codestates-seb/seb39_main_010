import React, { useState } from 'react';
import styled from 'styled-components';
import { IOptions } from 'pages/Study/Study';

export const SortingContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 9px 16px;
	margin-left: 15px;
	gap: 6px;

	height: 46px;

	background: #f9f9fc;
	border: 1px solid #cfcfcf;
	border-radius: 22px;
	select {
		float: right;
		padding: 0;
		background: none;
		border: none;
		outline: none;
		color: #000000;
		font-size: 15px;
		line-height: 40px;
	}
`;

interface Props {
	options: IOptions[];
}

const Filtering = ({ options }: Props) => {
	const [sorting, setSorting] = useState('최신순');
	console.log(sorting);

	return (
		<SortingContainer>
			<form>
				<select onClick={(e) => setSorting(e.currentTarget.value)}>
					{options.map((option: IOptions) => {
						return <option key={option.value}>{option.name}</option>;
					})}
				</select>
			</form>
		</SortingContainer>
	);
};
export default Filtering;
