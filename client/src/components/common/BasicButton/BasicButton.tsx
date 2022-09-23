import React, { ButtonHTMLAttributes } from 'react';
import styled from 'styled-components';

interface Props extends ButtonHTMLAttributes<HTMLButtonElement> {
	children: React.ReactNode;
	width?: string;
	height?: string;
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

const BasicButton = ({ mode = 'basic', children, ...props }: Props) => {
	const { bgColor, color } = ButtonOptions[mode];

	return (
		<StyledButton mode={mode} bgColor={bgColor} color={color} {...props}>
			<span>{children}</span>
		</StyledButton>
	);
};

export default BasicButton;

const StyledButton = styled.button<Props>`
	border: 1px solid #3563e9;
	border-radius: 10px;
	padding: 10px 20px;
	width: ${(props) => props.width ?? '100%'};
	height: ${(props) => props.height ?? '66px'};
	background-color: ${(props) => props.bgColor};
	color: ${(props) => props.color};
	font-size: 24px;
	font-weight: 600;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
`;
