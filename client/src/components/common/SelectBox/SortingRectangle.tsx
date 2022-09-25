import React from 'react';
import styled from 'styled-components';
import { IOptions } from 'pages/Study/Study';

export const SortingContainer = styled.div`
	/* Auto layout */

	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	padding: 10px 20px;

	width: 576px;
	height: 66px;

	/* Gray/300 */

	border: 1px solid #d2d5da;
	border-radius: 10px;

	select {
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
	return (
		<SortingContainer>
			<form>
				<select>
					{options.map((option: IOptions) => {
						return <option key={option.value}>{option.name}</option>;
					})}
				</select>
			</form>
		</SortingContainer>
	);
};
export default Filtering;
