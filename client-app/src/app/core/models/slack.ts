import { Student } from "./student";

export interface Slack {
    id?: number;
    url?: string;
    channelName?: string;
    channelId?: string;
    student?: Student;
    botToken?: string;
    studentId?: number;
    active?: boolean;
}