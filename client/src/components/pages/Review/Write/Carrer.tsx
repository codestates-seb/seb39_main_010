import { IOptions } from 'pages/Study/Study';
import React from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';

const CarrerContainer = styled.div`
	display: flex;
	flex-direction: row;
	align-items: center;
	padding: 10px 20px;

	width: 380px;
	height: 66px;

	border: 1px solid ${theme.colors.gray300};
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
		}
	}
`;
interface Props {
	options: IOptions[];
}

function selectBoxchange(event: React.FormEvent<HTMLSelectElement>) {
	const value = event.currentTarget.value;
	return value;
}

const Carrer = ({ options }: Props) => {
	return (
		<CarrerContainer>
			<form>
				<select required onChange={selectBoxchange}>
					{options.map((option: IOptions) => {
						return <option key={option.value}>{option.name}</option>;
					})}
				</select>
			</form>
		</CarrerContainer>
	);
};

export default Carrer;
