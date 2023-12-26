import {useEffect, useState} from "react";

export function WsrefCommentFormComponent({wsrefId, wsrefComment, insertWsrefComment, updateWsrefComment, onClose}: {
    wsrefId?: number,
    wsrefComment?: any,
    insertWsrefComment?: (wsrefCommentDto: any) => Promise<any>,
    updateWsrefComment?: (wsrefCommentDto: any) => Promise<any>,
    onClose: () => void
}) {
    let [comtext, setComtext] = useState("");
    useEffect(() => {
        if (wsrefComment) {
            setComtext(wsrefComment.comtext);
        }
    }, [wsrefComment]);
    return (
        <div>
            {wsrefComment && updateWsrefComment ? "update" : null}
            <div className={"flex"}>
                <textarea name={"comtext"} value={comtext} onChange={(e: any) => setComtext(e.target.value)}/>
            </div>
            <div>
                <button type={"button"} onClick={async (e: any) => {
                    if (!wsrefComment && insertWsrefComment) {
                        await insertWsrefComment({
                            wsrefId,
                            comtext
                        });
                        onClose();
                    }
                    if (wsrefComment && updateWsrefComment) {
                        await updateWsrefComment({
                            comtext
                        });
                        onClose();
                    }
                }}>OK
                </button>
                <button type={"button"} onClick={(e: any) => {
                    onClose();
                }}>Cancel
                </button>
            </div>
        </div>
    );
}