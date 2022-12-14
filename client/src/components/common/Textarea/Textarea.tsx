import React, { TextareaHTMLAttributes } from 'react';
import styled from 'styled-components';

export interface TextAreaProps
	extends TextareaHTMLAttributes<HTMLTextAreaElement> {
	placeholder: string;
	name?: string;
	value?: string;
	onChange?: (e: React.ChangeEvent<HTMLTextAreaElement>) => void;
	label?: string;
}

const TextArea = ({
	placeholder,
	name,
	value,
	onChange,
	label,
	...props
}: TextAreaProps) => {
	return (
		<TextAreaContainer>
			{label && <label>{label}</label>}
			<StyledTextArea
				placeholder={placeholder}
				name={name}
				value={value}
				onChange={onChange}
				{...props}
			/>
		</TextAreaContainer>
	);
};
export default TextArea;

const TextAreaContainer = styled.div`
	display: flex;
	flex-direction: column;

	& label {
		font-size: 18px;
		margin-bottom: 18px;
	}
`;

const StyledTextArea = styled.textarea<TextAreaProps>`
	margin-bottom: ${({ style }) => style?.marginBottom ?? '18px'};
	padding: 10px 20px;
	border: 1px solid #d2d5da;
	background-color: white;
	font-size: 18px;
	border-radius: 10px;

	width: 1200px;
	height: 405px;
	resize: none;

	& ::placeholder {
		color: #949494;
	}

	:focus {
		outline: none !important;
		border-color: #94caf3;
		box-shadow: 0 0 0px 4px #d6edfc;
	}
`;
