import {useEffect, useState} from "react";

export function WsrefFormComponent({wsref, insertWsref, updateWsref, onClose}: {
    wsref?: any,
    insertWsref?: (wsrefDto: any) => Promise<any>,
    updateWsref?: (wsrefDto: any) => Promise<any>,
    onClose: () => void
}) {
    let [url, setUrl] = useState("");
    let [description, setDescription] = useState("");
    useEffect(() => {
        if (wsref) {
            setUrl(wsref.url);
            setDescription(wsref.description);
        }
    }, [wsref]);
    return (
        <fieldset>
            <div className={"flex"}>
                <input name={"url"} value={url} placeholder={"http://url-useful-website-reference"}
                       onChange={(e: any) => setUrl(e.target.value)}/>
            </div>
            <div>
                <input name={"description"} value={description} placeholder={"key-word, key-word, ..."}
                       onChange={(e: any) => setDescription(e.target.value)}/>
            </div>
            <div>
                <button type={"button"} onClick={async (e: any) => {
                    if (!wsref && insertWsref) {
                        await insertWsref({
                            url,
                            description
                        });
                        onClose();
                    }
                    if (wsref && updateWsref) {
                        await updateWsref({
                            url,
                            description
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
        </fieldset>
    );
}