import React from 'react';
import styled from 'styled-components';
import { IOptions } from 'pages/Study/Study';

export const SortingContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 10px 20px;

	width: 576px;
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

interface Props {
	options: IOptions[];
}

const Filtering = ({ options }: Props) => {
	function selectBoxchange(event: React.FormEvent<HTMLSelectElement>) {
		const value = event.currentTarget.value;
		console.log(value); // 선택 옵션 잘나옴
		// parentFunction(value);
		return value;
	}

	return (
		<SortingContainer>
			<form onFocus={() => SortingContainer}>
				<select required onChange={selectBoxchange}>
					<option value="" disabled selected>
						직무 카테고리 선택
					</option>
					{options.map((option: IOptions) => {
						return <option key={option.value}>{option.name}</option>;
					})}
				</select>
			</form>
		</SortingContainer>
	);
};
export default Filtering;
