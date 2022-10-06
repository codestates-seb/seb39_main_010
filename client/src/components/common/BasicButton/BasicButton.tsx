import React, { ButtonHTMLAttributes } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
	children: React.ReactNode;
	disabled?: boolean;
	bgColor?: string;
	color?: string;
	mode?: 'basic' | 'login';
	onClick?: () => void;
}

interface ButtonOptions {
	[key: string]: {
		bgColor: Props['bgColor'];
		color: Props['color'];
	};
}

const ButtonOptions: ButtonOptions = {
	basic: {
		bgColor: '#3563E9',
		color: 'white',
	},
	login: {
		bgColor: 'white',
		color: '#3563E9',
	},
};

const BasicButton = ({
	mode = 'basic',
	disabled,
	children,
	...props
}: Props) => {
	const { bgColor, color } = ButtonOptions[mode];

	return (
		<StyledButton
			mode={mode}
			disabled={disabled}
			bgColor={bgColor}
			color={color}
			{...props}
		>
			{children}
		</StyledButton>
	);
};

export default BasicButton;

const StyledButton = styled.button<Props>`
	border: 1px solid #3563e9;
	border-radius: 10px;
	padding: 10px 20px;
	width: 100%;
	height: 66px;
	background-color: ${(props) => props.bgColor};
	color: ${(props) => props.color};
	font-size: 24px;
	font-weight: 600;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;

	&:disabled {
		border: 1px solid ${theme.colors.gray300};
		background-color: ${theme.colors.gray100};
		color: ${theme.colors.gray400};
		cursor: default;
	}
`;
